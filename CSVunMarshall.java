package com.test.camel.unmarshal

import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.math.BigDecimal;


/** route
DataFormat bindy = new BindyCsvDataFormat(Item.class);
from("{{startRoute}}")
                .log("Timer Invoked and the body" + environment.getProperty("message"))
                .to("{{toRoute1}}")
                .unmarshal(bindy)
                .log("The unmarshaled object is ${body}");
**/

@CsvRecord(separator = ",",skipFirstLine = true)
public class Item {

    @DataField(pos = 1)
    private String transactionType;

    @DataField(pos = 2)
    private String sku;

    @DataField(pos = 3)
    private String itemDescription;

    @DataField(pos = 4,precision = 2)
    private BigDecimal price;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "transactionType='" + transactionType + '\'' +
                ", sku='" + sku + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", price=" + price +
                '}';
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
  

}
