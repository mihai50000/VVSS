package mockLab4Tests;

import inventory.model.Product;
import inventory.repository.InventoryRepositoryFile;
import inventory.service.InventoryService;
import inventory.validator.PartValidator;
import inventory.validator.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class IntegrationRepositoryTest {
    InventoryService service;
    InventoryRepositoryFile repo;
    Product prod;
    Product prod2;

    @BeforeEach
    void setUp() {
        PartValidator partValidator = new PartValidator();
        ProductValidator productValidator = new ProductValidator();
        repo = new InventoryRepositoryFile(partValidator, productValidator);
        service = new InventoryService(repo);

        prod=mock(Product.class);
        prod2=mock(Product.class);

        repo.addProduct2(prod);
        repo.addProduct2(prod2);

        Mockito.when(prod.getName()).thenReturn("nume_gasit");
        Mockito.when(prod.getProductId()).thenReturn(1);

        Mockito.when(prod2.getName()).thenReturn("produs_second");
        Mockito.when(prod2.getProductId()).thenReturn(2);
    }

    @Test
    void lookupProductOkByName() {
        Product productFound=service.lookupProduct("nume_gasit");
        assertEquals(prod,productFound);
    }

    @Test
    void lookupProductNotOkByName() {
        Product productFound=service.lookupProduct("nume_negasit");
        assertNotEquals(prod2,productFound);
    }

}