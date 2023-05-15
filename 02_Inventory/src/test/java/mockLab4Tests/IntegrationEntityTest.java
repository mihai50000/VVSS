package mockLab4Tests;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.repository.InventoryRepositoryFile;
import inventory.service.InventoryService;
import inventory.validator.PartValidator;
import inventory.validator.ProductValidator;
import inventory.validator.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static inventory.validator.PartValidator.minErrMsg;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationEntityTest {
    InventoryService service;

    @BeforeEach
    void setUp() {
        PartValidator partValidator = new PartValidator();
        ProductValidator productValidator = new ProductValidator();
        InventoryRepositoryFile repo = new InventoryRepositoryFile(partValidator, productValidator);
        service = new InventoryService(repo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addInhousePart_validData_OK() {
        try {
            //setup
            Part part=new InhousePart(1,"Piulita", 0.5, 200, 5, 1000, 1);

            //act
            Part part_service=service.addInhousePart("Piulita", 0.5, 200, 5, 1000, 1);
            part.setPartId(part_service.getPartId());

            //assert
            assert(part_service.equals(part));
        } catch (ValidationException err) {
            assert (false);
        }
    }

    @Test
    void addInhousePart_InvalidMin_Exception() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            service.addInhousePart("Surub", 1, 200, -5, 1000, 34);
        });

        String expectedMessage = minErrMsg;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}