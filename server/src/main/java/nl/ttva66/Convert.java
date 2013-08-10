package nl.ttva66;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.DienstDto;
import nl.ttva66.dto.OpenDto;
import nl.ttva66.dto.TypeDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.entities.Dag;
import nl.ttva66.entities.Dienst;
import nl.ttva66.entities.Open;
import nl.ttva66.entities.Type;
import nl.ttva66.entities.Zaaldienst;

public class Convert {

	static ZaaldienstDto ZaaldientToDto(Zaaldienst zt) {
		ZaaldienstDto dto = new ZaaldienstDto();
		dto.setAantal(zt.getAantal());
		dto.setCanlogin(zt.isCanlogin());
		dto.setDinsdag(zt.isDinsdag());
		dto.setDonderdag(zt.isDonderdag());
		dto.setEmail(zt.getEmail());
		dto.setId(zt.getId());
		dto.setMaandag(zt.isMaandag());
		dto.setPassword(zt.getPassword());
		dto.setVrijdag(zt.isVrijdag());
		dto.setWoensdag(zt.isWoensdag());
		dto.setZaterdag(zt.isZaterdag());
		dto.setZondag(zt.isZondag());
		dto.setNaam(zt.getNaam());

		Set<DienstDto> ddto = new TreeSet<DienstDto>(new Comparator<DienstDto>() {
			public int compare(DienstDto n1, DienstDto n2) {
				return n1.getType().getSequence() - n2.getType().getSequence();
			}
		});
		;

		for (Dienst d : zt.getDiensts()) {
			ddto.add(DienstToDto(d));
		}

		dto.setDiensts(ddto);

		System.out.println("DTO is hier: " + dto);

		return dto;
	}

	static DienstDto DienstToDto(Dienst dt) {
		DienstDto dto = new DienstDto();
		dto.setBackup(dt.isBackup());
		dto.setDag(dt.getDag().getId());
		dto.setDefinitief(dt.isDefinitief());
		dto.setId(dt.getId());
		dto.setType(TypeToDto(dt.getType()));
		dto.setZaaldienst(dt.getZaaldienst().getId());

		return dto;
	}

	static TypeDto TypeToDto(Type type) {
		TypeDto dto = new TypeDto();
		dto.setEind(type.getEind());
		dto.setId(type.getId());
		dto.setNaam(type.getNaam());
		dto.setStart(type.getStart());
		dto.setSequence(type.getSequence());

		return dto;
	}

	static OpenDto OpenToDto(Open op) {
		OpenDto dto = new OpenDto();
		dto.setDag(op.getDag().getId());
		dto.setId(op.getId());
		dto.setOpen(op.isOpen());
		dto.setType(TypeToDto(op.getType()));

		return dto;
	}

	static DagDto DagToDto(Dag zt) {
		DagDto dto = new DagDto();
		dto.setDag(zt.getDag());
		dto.setMaand(zt.getMaand());
		dto.setJaar(zt.getJaar());
		dto.setId(zt.getId());
		dto.setOpmerkingen(zt.getOpmerkingen());
		dto.setTeam(zt.getTeam());

		Set<OpenDto> odto = new TreeSet<OpenDto>(new Comparator<OpenDto>() {
			public int compare(OpenDto n1, OpenDto n2) {
				return n1.getType().getSequence() - n2.getType().getSequence();
			}
		});
		;

		for (Open op : zt.getOpens()) {
			odto.add(OpenToDto(op));
		}
		System.out.println(odto);

		dto.setOpens(odto);

		Set<DienstDto> odie = new TreeSet<DienstDto>();

		for (Dienst dt : zt.getDiensts()) {
			odie.add(DienstToDto(dt));
		}

		dto.setDienst(odie);

		System.out.println("DTO size: " + dto.getOpens().size());

		return dto;
	}

	static Type DtoToType(TypeDto type) {
		Type dto = new Type();
		dto.setEind(type.getEind());
		dto.setId(type.getId());
		dto.setNaam(type.getNaam());
		dto.setStart(type.getStart());
		dto.setSequence(type.getSequence());

		return dto;
	}

	static Dag DtoToDag(DagDto zt, EntityManager em) {
		Dag dto = new Dag();
		dto.setDag(zt.getDag());
		dto.setMaand(zt.getMaand());
		dto.setJaar(zt.getJaar());
		dto.setId(zt.getId());
		dto.setOpmerkingen(zt.getOpmerkingen());
		dto.setTeam(zt.getTeam());

		Set<Open> odto = new HashSet<Open>();

		for (OpenDto opdto : zt.getOpens()) {
			Open op = new Open();
			op.setDag(dto);
			op.setType(DtoToType(opdto.getType()));
			op.setOpen(opdto.isOpen());
			op.setId(opdto.getId());

			odto.add(op);
		}
		dto.setOpens(odto);

		Set<Dienst> odie = new HashSet<Dienst>();

		for (DienstDto dt : zt.getDienst()) {
			Dienst d = new Dienst();
			d.setBackup(dt.isBackup());
			d.setDag(dto);
			d.setDefinitief(dt.isDefinitief());
			d.setId(dt.getId());
			d.setType(DtoToType(dt.getType()));

			Query rs = em
					.createQuery("SELECT X FROM Zaaldienst as X WHERE id = :id");
			rs.setParameter("id", dt.getZaaldienst());
			Zaaldienst z = (Zaaldienst) rs.getSingleResult();

			d.setZaaldienst(z);
			odie.add(d);
		}
		dto.setDiensts(odie);

		return dto;
	}
}
