import dto.ProductDto;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
public class DashboardPage implements Serializable {

    private List<ProductDto> productDtoList;

    public DashboardPage(){
        this.productDtoList = new ArrayList<>();
        this.productDtoList.add(new ProductDto("Product_1"));
        this.productDtoList.add(new ProductDto("Product_2"));
        this.productDtoList.add(new ProductDto("Product_3"));
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
