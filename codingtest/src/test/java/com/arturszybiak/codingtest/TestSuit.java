package com.arturszybiak.codingtest;

import com.arturszybiak.codingtest.controllers.ValidationControllerTest;
import com.arturszybiak.codingtest.facade.ValidationFacadeTest;
import com.arturszybiak.codingtest.utils.ValidationRulesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ValidationControllerTest.class,
        ValidationFacadeTest.class,
        ValidationRulesTest.class,
})
public class TestSuit {

}
