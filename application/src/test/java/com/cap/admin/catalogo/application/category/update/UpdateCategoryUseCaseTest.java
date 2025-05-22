package com.cap.admin.catalogo.application.category.update;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cap.admin.catalogo.application.UseCaseTest;
import com.cap.admin.catalogo.domain.category.Category;
import com.cap.admin.catalogo.domain.category.CategoryGateway;
import com.cap.admin.catalogo.domain.category.CategoryID;
import com.cap.admin.catalogo.domain.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest extends UseCaseTest {

        @InjectMocks
        private DefaultUpdateCategoryUseCase useCase;

        @Mock
        private CategoryGateway categoryGateway;

        @Override
        protected List<Object> getMocks() {
                return List.of(categoryGateway);
        }

        @Test
        void givenValidCommand_whenExecute_thenShouldReturnSuccess() {
                // Arrange
                final var aCategory = Category.newCategory("Movies", "Category for movies", true);

                final var expectedId = aCategory.getId();
                final var expectedName = "Updated Movies";
                final var expectedDescription = "Updated Category for movies";
                final var expectedIsActive = true;

                when(categoryGateway.findById(eq(expectedId)))
                                .thenReturn(Optional.of(aCategory.clone()));

                when(categoryGateway.update(any()))
                                .thenAnswer(returnsFirstArg());

                // Act
                final var aCommand = UpdateCategoryCommand.with(
                                expectedId.getValue(),
                                expectedName,
                                expectedDescription,
                                expectedIsActive);
                final var useCase = new DefaultUpdateCategoryUseCase(categoryGateway);
                final var actualOutput = useCase.execute(aCommand).get();

                // Assert
                Assertions.assertNotNull(actualOutput);
                Assertions.assertEquals(expectedId.getValue(), actualOutput.id());

                Mockito.verify(categoryGateway, times(1)).findById(eq(expectedId));

                Mockito.verify(categoryGateway, times(1))
                                .update(argThat(anUpdatedCategory ->

                                Objects.equals(expectedName, anUpdatedCategory.getName())
                                                && Objects.equals(expectedDescription,
                                                                anUpdatedCategory.getDescription())
                                                && Objects.equals(expectedIsActive,
                                                                anUpdatedCategory.isActive())
                                                && Objects.equals(expectedId, anUpdatedCategory.getId())
                                                && Objects.equals(aCategory.getCreatedAt(),
                                                                anUpdatedCategory.getCreatedAt())
                                                && aCategory.getUpdatedAt().isBefore(anUpdatedCategory.getUpdatedAt())
                                                && Objects.isNull(anUpdatedCategory.getDeletedAt())

                                ));
        }

        @Test
        public void givenAInvalidName_whenCallsUpdateCategory_thenShouldReturnDomainException() {
                final var aCategory = Category.newCategory("Movie", null, true);

                final String expectedName = null;
                final var expectedDescription = "The most watched movie";
                final var expectedIsActive = true;
                final var expectedId = aCategory.getId();

                final var expectedErrorMessage = "'name' should not be null";
                final var expectedErrorCount = 1;

                final var aCommand = UpdateCategoryCommand.with(expectedId.getValue(), expectedName,
                                expectedDescription, expectedIsActive);

                when(categoryGateway.findById(eq(expectedId)))
                                .thenReturn(Optional.of(Category.with(aCategory)));

                final var notification = useCase.execute(aCommand).getLeft();

                Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
                Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

                Mockito.verify(categoryGateway, times(0)).update(any());
        }

        @Test
        public void givenAValidInactivateCommand_whenCallsUpdateCategory_shouldReturnInactiveCategoryId() {
                final var aCategory = Category.newCategory("Movie", null, true);

                final var expectedName = "Movies";
                final var expectedDescription = "The most watched movie";
                final var expectedIsActive = false;
                final var expectedId = aCategory.getId();

                final var aCommand = UpdateCategoryCommand.with(
                                expectedId.getValue(),
                                expectedName,
                                expectedDescription,
                                expectedIsActive);

                when(categoryGateway.findById(eq(expectedId)))
                                .thenReturn(Optional.of(Category.with(aCategory)));

                when(categoryGateway.update(any()))
                                .thenAnswer(returnsFirstArg());

                Assertions.assertTrue(aCategory.isActive());
                Assertions.assertNull(aCategory.getDeletedAt());

                final var actualOutput = useCase.execute(aCommand).get();

                Assertions.assertNotNull(actualOutput);
                Assertions.assertNotNull(actualOutput.id());

                Mockito.verify(categoryGateway, times(1)).findById(eq(expectedId));

                Mockito.verify(categoryGateway, times(1)).update(argThat(
                                aUpdatedCategory -> Objects.equals(expectedName, aUpdatedCategory.getName())
                                                && Objects.equals(expectedDescription,
                                                                aUpdatedCategory.getDescription())
                                                && Objects.equals(expectedIsActive, aUpdatedCategory.isActive())
                                                && Objects.equals(expectedId, aUpdatedCategory.getId())
                                                && Objects.equals(aCategory.getCreatedAt(),
                                                                aUpdatedCategory.getCreatedAt())
                                                && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                                                && Objects.nonNull(aUpdatedCategory.getDeletedAt())));
        }

        @Test
        public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException() {
                final var aCategory = Category.newCategory("Movie", null, true);

                final var expectedName = "Movies";
                final var expectedDescription = "The most watched movie";
                final var expectedIsActive = true;
                final var expectedId = aCategory.getId();
                final var expectedErrorCount = 1;
                final var expectedErrorMessage = "Gateway error";

                final var aCommand = UpdateCategoryCommand.with(
                                expectedId.getValue(),
                                expectedName,
                                expectedDescription,
                                expectedIsActive);

                when(categoryGateway.findById(eq(expectedId)))
                                .thenReturn(Optional.of(Category.with(aCategory)));

                when(categoryGateway.update(any()))
                                .thenThrow(new IllegalStateException(expectedErrorMessage));

                final var notification = useCase.execute(aCommand).getLeft();

                Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
                Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

                Mockito.verify(categoryGateway, times(1)).update(argThat(
                                aUpdatedCategory -> Objects.equals(expectedName, aUpdatedCategory.getName())
                                                && Objects.equals(expectedDescription,
                                                                aUpdatedCategory.getDescription())
                                                && Objects.equals(expectedIsActive, aUpdatedCategory.isActive())
                                                && Objects.equals(expectedId, aUpdatedCategory.getId())
                                                && Objects.equals(aCategory.getCreatedAt(),
                                                                aUpdatedCategory.getCreatedAt())
                                                && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                                                && Objects.isNull(aUpdatedCategory.getDeletedAt())));
        }

        @Test
        public void givenACommandWithInvalidID_whenCallsUpdateCategory_shouldReturnNotFoundException() {
                final var expectedName = "Movies";
                final var expectedDescription = "The most watched movie";
                final var expectedIsActive = false;
                final var expectedId = "123";
                final var expectedErrorMessage = "Category with ID 123 was not found";

                final var aCommand = UpdateCategoryCommand.with(
                                expectedId,
                                expectedName,
                                expectedDescription,
                                expectedIsActive);

                when(categoryGateway.findById(eq(CategoryID.from(expectedId))))
                                .thenReturn(Optional.empty());

                final var actualException = Assertions.assertThrows(NotFoundException.class,
                                () -> useCase.execute(aCommand));

                Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

                Mockito.verify(categoryGateway, times(1)).findById(eq(CategoryID.from(expectedId)));

                Mockito.verify(categoryGateway, times(0)).update(any());
        }
}
