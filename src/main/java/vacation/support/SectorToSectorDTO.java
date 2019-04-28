package vacation.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vacation.model.Sector;
import vacation.web.dto.SectorDTO;

@Component
public class SectorToSectorDTO 
	implements Converter<Sector, SectorDTO>{

	public SectorDTO convert(Sector source) {
		
		SectorDTO sectorDTO = new SectorDTO();
		sectorDTO.setId(source.getId());
		sectorDTO.setSectorName(source.getSectorName());
		sectorDTO.setBonus(source.getBonus());
		return sectorDTO;
	}
	
	public List<SectorDTO> convert(List<Sector> sectors){
		List<SectorDTO> ret = new ArrayList<SectorDTO>();
		
		for(Sector s : sectors) {
			ret.add(convert(s));
		}
		return ret;
	}
}
