package com.eolivenza.modules.baseProject.domain.model;

import org.junit.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


public class EntityTest {

    @Test
    public void equals_is_false_for_different_identities() {
        StubEntity instance1 = new StubEntity(1);
        StubEntity instance2 = new StubEntity(2);

        assertThat(instance1.equals(instance2)).isFalse();
    }

    @Test
    public void hashCode_is_called() {
        StubEntity instance1 = new StubEntity(1);
        instance1.hashCode();
    }

    static class StubEntity extends Entity<StubEntity> {

        private Integer entityId;

        public StubEntity(int entityId) {
            this.entityId = entityId;
        }

        public boolean hasSameIdentity(StubEntity other) {
            return Objects.equals(entityId, other.entityId);
        }

        @Override
        public int hashCodeCalculation() {
            return 0;
        }
    }
}