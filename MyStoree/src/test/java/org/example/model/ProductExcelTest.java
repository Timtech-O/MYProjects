package org.example.model;

import junit.framework.TestCase;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductExcelTest extends TestCase {

    public void testExcel() throws IOException {
        Store store  = new Store(10,"Timo");
        ProductExcel proo = new ProductExcel(store);
        proo.Excel(MyFilePath.FILE_PATH);
        assertEquals(13, store.getProductList().size());
    }
}