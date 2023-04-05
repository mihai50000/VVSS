package inventory.service;

import inventory.repository.PartRepository;

public class PartService {
    private PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }
}
