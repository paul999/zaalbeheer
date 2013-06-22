package nl.ttva66;

import java.util.HashSet;
import java.util.Set;

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

	static ZaaldienstDto ZaaldientToDto(Zaaldienst zt)
	{
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
		
		Set<DienstDto> ddto = new HashSet<DienstDto>();
		
		for (Dienst d : zt.getDiensts())
		{
			ddto.add(DienstToDto(d));
		}
		
		dto.setDiensts(ddto);
		
		return dto;
	}
	static DienstDto DienstToDto(Dienst dt)
	{
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
		
		return dto;
	}
	
	static OpenDto OpenToDto(Open op)
	{
		OpenDto dto = new OpenDto();
		dto.setDag(op.getDag().getId());
		dto.setId(op.getId());
		dto.setOpen(op.isOpen());
		dto.setType(TypeToDto(op.getType()));
		
		return dto;
	}
	
	static DagDto DagToDto(Dag zt) {
		DagDto dto = new DagDto();
		dto.setDatum(zt.getDatum());
		dto.setId(zt.getId());
		dto.setOpmerkingen(zt.getOpmerkingen());
		dto.setTeam(zt.getTeam());
		
		
		
		return dto;
	}
}
