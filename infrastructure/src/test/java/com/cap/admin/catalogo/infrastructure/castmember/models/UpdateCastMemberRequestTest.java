package com.cap.admin.catalogo.infrastructure.castmember.models;

import com.cap.admin.catalogo.domain.Fixture;
import com.cap.admin.catalogo.JacksonTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

@JacksonTest
class UpdateCastMemberRequestTest {

  @Autowired
  private JacksonTester<UpdateCastMemberRequest> json;

  @Test
  public void testUnmarshall() throws Exception {
    final var expectedName = Fixture.name();
    final var expectedType = Fixture.CastMembers.type();

    final var json = """
        {
          "name": "%s",
          "type": "%s"
        }
        """.formatted(expectedName, expectedType);

    final var actualJson = this.json.parse(json);

    Assertions.assertThat(actualJson)
        .hasFieldOrPropertyWithValue("name", expectedName)
        .hasFieldOrPropertyWithValue("type", expectedType);
  }
}