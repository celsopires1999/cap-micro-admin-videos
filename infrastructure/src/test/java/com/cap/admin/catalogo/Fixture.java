package com.cap.admin.catalogo;

import com.cap.admin.catalogo.domain.castmember.CastMemberType;
import net.datafaker.Faker;

public final class Fixture {

    private static final Faker FAKER = new Faker();

    public static String name() {
        // return "John Doe";
        return FAKER.name().fullName();
    }

    public static final class CastMember {

        public static CastMemberType type() {
            // return CastMemberType.ACTOR;
            return FAKER.options()
                    .option(CastMemberType.ACTOR, CastMemberType.DIRECTOR);
        }
    }
}
