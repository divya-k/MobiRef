package com.simple.ams.util;

import com.google.common.collect.ImmutableList;
import cucumber.api.junit.Cucumber;
import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.model.CucumberFeature;
import cucumber.runtime.model.CucumberScenario;
import cucumber.runtime.model.CucumberTagStatement;
import gherkin.formatter.model.Step;
import org.junit.runners.model.InitializationError;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CustomRunner extends Cucumber {
    private static Runtime runtime;
    private static ResourceLoader resourceLoader;

    private static final List<String> featurePath = ImmutableList.of("src/test/resources/feature");

    public CustomRunner(Class clazz) throws InitializationError, IOException {
        super(clazz);
    }

    protected Runtime createRuntime(ResourceLoader resourceLoader, ClassLoader classLoader, RuntimeOptions runtimeOptions){
        ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
        CustomRunner.resourceLoader = resourceLoader;
        return (CustomRunner.runtime = new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions));
    }

    public static void runFeature(String scenarioTag) {
        if (CustomRunner.runtime == null)
            throw new AssertionError("Custom runner configuration error");

        List<CucumberFeature> features = CucumberFeature.load(resourceLoader, featurePath,
                Collections.singletonList(scenarioTag), System.out);
        for (CucumberFeature feature : features) {
            List<CucumberTagStatement> elements = feature.getFeatureElements();
            for (CucumberTagStatement element : elements) {
                if (element instanceof CucumberScenario) {
                    CucumberScenario scenario = (CucumberScenario) element;
                    List<Step> steps = scenario.getSteps();
                    for (Step step : steps) {
                        try {
                            runtime.runUnreportedStep(feature.getPath(), feature.getI18n(), step.getKeyword(),
                                    step.getName(), step.getLine(), step.getRows(), step.getDocString());
                        } catch (Throwable t) {
                            t.printStackTrace();
                            throw new Error(t);
                        }
                    }
                }
            }
        }
    }

}
