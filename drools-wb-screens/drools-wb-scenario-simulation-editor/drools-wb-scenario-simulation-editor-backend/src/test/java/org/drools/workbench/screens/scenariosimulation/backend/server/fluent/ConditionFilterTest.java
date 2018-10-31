/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.scenariosimulation.backend.server.fluent;

import java.util.Optional;
import java.util.function.Function;

import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ScenarioResult;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValue;
import org.junit.Assert;
import org.junit.Test;

import static java.util.Arrays.asList;

public class ConditionFilterTest {

    @Test
    public void acceptTest() {
        Function<Object, Optional<Object>> alwaysMatchFunction = Optional::of;
        FactMappingValue factMappingValue = new FactMappingValue(FactIdentifier.DESCRIPTION, ExpressionIdentifier.DESCRIPTION, "Test");
        ScenarioResult scenarioResult = new ScenarioResult(FactIdentifier.DESCRIPTION, factMappingValue);
        ConditionFilter conditionFilter = new ConditionFilter(asList(new FactCheckerHandle(String.class, alwaysMatchFunction, scenarioResult)));

        Assert.assertFalse(conditionFilter.accept(1));
        Assert.assertTrue(conditionFilter.accept("String"));

        Function<Object, Optional<Object>> alwaysNotMatchFunction = object -> Optional.empty();
        ConditionFilter conditionFilterFail = new ConditionFilter(asList(new FactCheckerHandle(String.class, alwaysNotMatchFunction, scenarioResult)));
        Assert.assertFalse(conditionFilterFail.accept("String"));
    }
}