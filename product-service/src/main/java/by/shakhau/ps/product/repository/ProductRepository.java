package by.shakhau.ps.product.repository;

import by.shakhau.ps.product.repository.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {

    @Query("UPDATE ProductEntity SET deleted = :deleted WHERE id = :id")
    @Modifying
    void updateDeleted(UUID id, Boolean deleted);

    @Query(
            value = """
                SELECT * FROM products WHERE 
                (
                    name ILIKE ALL(:searchWords) 
                    OR description ILIKE ALL(:searchWords)
                ) AND deleted = :deleted
                """,
            countQuery = """
                SELECT COUNT(id) FROM products WHERE 
                (name ILIKE ALL(:searchWords) OR description ILIKE ALL(:searchWords)) 
                AND deleted = :deleted
                """,
            nativeQuery = true
    )
    Page<ProductEntity> findBySearchAndDeleted(String[] searchWords, Boolean deleted, Pageable pageable);

    @Query(
            value = """
                SELECT * FROM products WHERE 
                (
                    name ILIKE ALL(:searchWords) 
                    OR description ILIKE ALL(:searchWords)
                )
                """,
            countQuery = """
                SELECT COUNT(id) FROM products WHERE 
                (name ILIKE ALL(:searchWords) OR description ILIKE ALL(:searchWords)) 
                """,
            nativeQuery = true
    )
    Page<ProductEntity> findBySearch(String[] searchWords, Pageable pageable);
}
