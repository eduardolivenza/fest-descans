package com.eolivenza.modules.baseProject.application.configuration.commands.overwrite;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OverwriteConfigurationCommandTest {
    @Test
    public void call_to_constructor() {
        OverwriteConfigurationCommand overwriteConfigurationCommand = OverwriteConfigurationCommandDataBuilder.defaultBuilder().build();
        assertThat(overwriteConfigurationCommand).isNotNull();
    }
}