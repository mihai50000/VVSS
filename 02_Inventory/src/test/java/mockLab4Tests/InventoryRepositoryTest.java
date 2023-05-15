package mockLab4Tests;

import inventory.model.Product;
import inventory.repository.InventoryRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class InventoryRepositoryTest {

    InventoryRepositoryInMemory repo;
    InventoryRepositoryInMemory emptyListRepo;

    Product prod;
    Product prod2;
    Product prod3;

    @BeforeEach
    void setUp() {
        repo= new InventoryRepositoryInMemory();

//        prod=new Product(1, "nume_gasit", 1.0, 0, 0, 0, null);
//        prod2=new Product(2, "produs_second", 2.0, 0, 0, 0, null);
//        prod3=new Product(3, "prod", 3.0, 0, 0, 0, null);
        prod=mock(Product.class);
        prod2=mock(Product.class);
        prod3=mock(Product.class);

        Mockito.when(prod.getName()).thenReturn("nume_gasit");
        Mockito.when(prod2.getName()).thenReturn("produs_second");
        Mockito.when(prod3.getName()).thenReturn("prod");

        Mockito.when(prod.getProductId()).thenReturn(1);
        Mockito.when(prod2.getProductId()).thenReturn(2);
        Mockito.when(prod3.getProductId()).thenReturn(3);

        repo.addProduct(prod);
        repo.addProduct(prod2);
        repo.addProduct(prod3);

        emptyListRepo= new InventoryRepositoryInMemory();
    }

    @Test
    void lookupProductOkByName() {
        Product productFound=repo.lookupProduct("nume_gasit");

        assertEquals(prod,productFound);
    }

    @Test
    void lookupProductOkById() {
        Product productFound=repo.lookupProduct("2");

        assertEquals(prod2,productFound);
    }

    @Test
    void lookupProductNoSearchResults() {
        Product productFound=repo.lookupProduct("nu_se_gaseste");

        assertNull(productFound);
    }

    @Test
    void lookupProductEmptyList() {
        Product emptyProduct=new Product(0,null,0.0,0,0,0,null);

        Product productFound=emptyListRepo.lookupProduct("lista_nepopulata");

        assertEquals(emptyProduct,productFound);
    }

    @Test
    void lookupProduct4thElem() {
        Product productFound=repo.lookupProduct("produs_second");

        assertEquals(prod2,productFound);
    }
}