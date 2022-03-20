package br.com.casare86.mundi.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract interface Converter<Target, Source> {

	//private final Function<Target, Source> fromTarget;
	//private final Function<Source, Target> fromSource;
	
//	public Converter(final Function<Target, Source> fromTarget, final Function<Source, Target> fromSource) {
//	    this.fromTarget = fromTarget;
//	    this.fromSource = fromSource;
//	}
	
	Source toEntity(Target dto);
	
	Target toDTO(Source entity);
	
	Source updateEntity(Source entity, Target dto);
	
	 default List<Source> createFromDtos(final Collection<Target> dtos) {
	    return dtos.stream().map(this::toEntity).collect(Collectors.toList());
	  }

	 default List<Target> createFromEntities(final Collection<Source> entities) {
	    return entities.stream().map(this::toDTO).collect(Collectors.toList());
	  }
}
