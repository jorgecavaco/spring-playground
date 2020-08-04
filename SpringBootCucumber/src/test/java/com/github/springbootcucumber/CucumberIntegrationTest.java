package com.github.springbootcucumber;

import com.github.springbootcucumber.configs.SpringIntegrationTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberIntegrationTest extends SpringIntegrationTest {

}
