package exercise.mapper;


import exercise.dto.ProductCreateDTO;
import exercise.model.Product;
import org.mapstruct.*;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.dto.ProductDTO;
import exercise.model.Product;
// BEGIN
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {

    @Mapping(source = "title", target = "name")
    @Mapping(source = "price", target = "cost")
    @Mapping(source = "vendorCode", target = "barcode")
    //используется для создания нового объекта Post на основе данных из PostCreateDTO
    public abstract Product map(ProductCreateDTO dto);

    @Mapping(source = "price", target = "cost")
    //используется для обновления существующего объекта Post на основе данных из PostUpdateDTO
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode", source = "barcode")
    //используется для преобразования объекта Post в объект PostDTO,
    //который будет использоваться для передачи данных на клиентскую сторону
    public abstract ProductDTO map(Product model);
}
// END
