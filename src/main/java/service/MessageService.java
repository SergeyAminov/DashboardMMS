package service;

import dto.ProductDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MessageService {

    private List<ProductDto> productDtoList;

    @PostConstruct
    public void init(){
        this.productDtoList = new ArrayList<>();
        this.productDtoList.add(new ProductDto("Product_1"));
        this.productDtoList.add(new ProductDto("Product_2"));
        this.productDtoList.add(new ProductDto("Product_3"));
    }

    @Produces
    @Named
    public List<ProductDto> getProductDtoList(){
        return this.productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

}
