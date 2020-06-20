package tolls;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.MappingTarget;

public interface AbstractMapper<T,D> {
	
    T toEntity(D dto);

    @InheritInverseConfiguration(name = "toEntity")
    D toDto(T entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<D> toDtoList(List<T> entities);

    @IterableMapping(qualifiedByName = "toEntity")
    List<T> toEntityList(List<D> dtos);

    @InheritInverseConfiguration(name = "toDto")
    void updateModel(D dto, @MappingTarget T entity);
	
}
