package com.cap.admin.catalogo.application.category.create;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cap.admin.catalogo.domain.category.CategoryGateway;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

        @InjectMocks
        private DefaultCreateCategoryUseCase useCase;

        @Mock
        private CategoryGateway categoryGateway;

        @BeforeEach
        void cleanUp() {
                Mockito.reset(categoryGateway);
        }

        @Test
        public void givenAValidCommand_whenCallsCreateCategory_thenShouldReturnCategoryId() {
                final var expectedName = "Movies";
                final var expectedDescription = "The most watched category";
                final var expectedIsActive = true;

                final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

                when(categoryGateway.create(any()))
                                .thenAnswer(returnsFirstArg());

                final var actualOutput = useCase.execute(aCommand).get();

                Assertions.assertNotNull(actualOutput);
                Assertions.assertNotNull(actualOutput.id());

                Mockito.verify(categoryGateway, times(1))
                                .create(argThat(aCategory -> Objects.equals(expectedName, aCategory.getName())
                                                && Objects.equals(expectedDescription, aCategory.getDescription())
                                                && Objects.equals(expectedIsActive, aCategory.isActive())
                                                && Objects.nonNull(aCategory.getId())
                                                && Objects.nonNull(aCategory.getCreatedAt())
                                                && Objects.nonNull(aCategory.getUpdatedAt())
                                                && Objects.isNull(aCategory.getDeletedAt())));
        }

        @Test
        public void givenInvalidName_whenCallsCreateCategory_thenShouldReturnDomainException() {
                final String expectedName = null;
                final String expectedDescription = "The most watched category";
                final boolean expectedIsActive = true;
                final var expectedErrorMessage = "'name' should not be null";
                final var expectedErrorCount = 1;

                final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);
                final var notification = useCase.execute(aCommand).getLeft();

                Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
                Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

                Mockito.verify(categoryGateway, times(0)).create(any());
        }

        @Test
        public void givenAValidCommandWithInactiveCategory_whenCallsCreateCategory_thenShouldReturnInactiveCategoryId() {
                final var expectedName = "Movies";
                final var expectedDescription = "The most watched category";
                final var expectedIsActive = false;

                final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

                when(categoryGateway.create(any()))
                                .thenAnswer(returnsFirstArg());

                final var actualOutput = useCase.execute(aCommand).get();

                Assertions.assertNotNull(actualOutput);
                Assertions.assertNotNull(actualOutput.id());

                Mockito.verify(categoryGateway, times(1))
                                .create(argThat(aCategory -> Objects.equals(expectedName, aCategory.getName())
                                                && Objects.equals(expectedDescription, aCategory.getDescription())
                                                && Objects.equals(expectedIsActive, aCategory.isActive())
                                                && Objects.nonNull(aCategory.getId())
                                                && Objects.nonNull(aCategory.getCreatedAt())
                                                && Objects.nonNull(aCategory.getUpdatedAt())
                                                && Objects.nonNull(aCategory.getDeletedAt())));
        }

        @Test
        public void givenAValidCommand_whenGatewayThrowsRandomException_thenShouldReturnAnException() {
                final var expectedName = "Movies";
                final var expectedDescription = "The most watched category";
                final var expectedIsActive = true;
                final var expectedErrorMessage = "Gateway error";
                final var expectedErrorCount = 1;

                final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

                when(categoryGateway.create(any()))
                                .thenThrow(new IllegalStateException(expectedErrorMessage));

                final var notification = useCase.execute(aCommand).getLeft();

                Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
                Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

                Mockito.verify(categoryGateway, times(1))
                                .create(argThat(aCategory -> Objects.equals(expectedName, aCategory.getName())
                                                && Objects.equals(expectedDescription, aCategory.getDescription())
                                                && Objects.equals(expectedIsActive, aCategory.isActive())
                                                && Objects.nonNull(aCategory.getId())
                                                && Objects.nonNull(aCategory.getCreatedAt())
                                                && Objects.nonNull(aCategory.getUpdatedAt())
                                                && Objects.isNull(aCategory.getDeletedAt())));
        }
}
