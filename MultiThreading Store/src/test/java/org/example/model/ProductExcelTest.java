package org.example.model;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductExcelTest extends TestCase {



    @Test
    public void testExcel() throws IOException {
        Store store = new Store(10,"store");
        ProductExcel proo = new ProductExcel(store);
        proo.Excel("/Users/decagon/Documents/MyStoree - Copy/src/main/resources/Product Excel File/Product.xlsx");
    }
}