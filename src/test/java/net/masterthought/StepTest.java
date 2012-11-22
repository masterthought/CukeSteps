package net.masterthought;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepTest {
    @Test
    public void shouldBeAbleToRetrieveID() throws Exception {
       Step step = new Step(1,"Given I have some data","/features/feature1.feature");
       assertThat(step.getId(),is(1));
    }

    @Test
    public void shouldBeAbleToRetrieveText() throws Exception {
        Step step = new Step(1,"Given I have some data","/features/feature1.feature");
        assertThat(step.getText(),is("Given I have some data"));
    }

    @Test
    public void  shouldBeAbleToCompareTwoStepsToEachOtherForSortingPurposes() throws Exception {
        Step step1 = new Step(1,"Given I have some data","/features/feature1.feature");
        Step step2 = new Step(2,"Given I have some data","/features/feature2.feature");
        assertThat(step1.compareTo(step2), is(-1));
    }
}
