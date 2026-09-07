package by.shakhau.ps.product.service.impl;

import by.shakhau.ps.core.service.exception.ResourceForbiddenException;
import by.shakhau.ps.core.service.exception.ResourceNotFoundException;
import by.shakhau.ps.product.repository.ProductRepository;
import by.shakhau.ps.product.repository.entity.ProductEntity;
import by.shakhau.ps.product.service.ProductService;
import by.shakhau.ps.product.service.mapper.ProductMapper;
import by.shakhau.ps.product.service.model.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;

    @Override
    public List<Product> findByIdIn(List<UUID> ids) {
        return repository.findAllById(ids).stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public Page<Product> findAll(String search, Boolean deleted, Pageable pageable) {
        String[] searchString = Arrays.stream(search.toLowerCase().split("\\s+"))
                .filter(w -> !w.isEmpty())
                .map(w -> "%" + w + "%")
                .toArray(String[]::new);

        if (deleted != null) {
            return repository.findBySearchAndDeleted(searchString, deleted, pageable).map(mapper::toModel);
        }

        return repository.findBySearch(searchString, pageable).map(mapper::toModel);
    }

    @Override
    public Product findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toModel)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with id = %s nof found".formatted(id)));
    }

    @Override
    public List<Product> create(List<Product> products) {
        List<ProductEntity> savedProducts = repository.saveAll(
                products.stream()
                        .map(p -> mapper.toEntity(false, p))
                        .toList());
        return savedProducts.stream().map(mapper::toModel).toList();
    }

    @Transactional
    @Override
    public Product create(Product product) {
        if (product.getId() != null) {
            throw new ResourceForbiddenException("Product ID must be null");
        }

        return mapper.toModel(repository.save(mapper.toEntity(false, product)));
    }

    @Transactional
    @Override
    public Product update(Product product) {
        if (product.getId() == null) {
            throw new ResourceForbiddenException("Product ID must not be null");
        }

        ProductEntity foundProduct = repository.findById(product.getId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Product with id = %s not found".formatted(product.getId())));

        mapper.update(product, foundProduct);

        return mapper.toModel(repository.save(foundProduct));
    }

    @Transactional
    @Override
    public void updateDeleted(UUID id, Boolean deleted) {
        repository.updateDeleted(id, deleted);
    }
}
