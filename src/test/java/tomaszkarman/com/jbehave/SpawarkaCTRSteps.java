package tomaszkarman.com.jbehave;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import tomaszkarman.com.domain.SpawarkaCTR;
import tomaszkarman.com.service.SpawarkiServiceImpl;

import static org.junit.Assert.assertEquals;

public class SpawarkaCTRSteps {

    private SpawarkaCTR spawarkaCTR;
    private SpawarkiServiceImpl spawarkiService;

    @Given("a spawarka")
    public void spawarkaSetup() {
        spawarkaCTR = new SpawarkaCTR();
        spawarkiService = new SpawarkiServiceImpl();
    }

    @When("set arguments like name: $a, model: $b, code: $c")
    public void createNewSpawarka(String a, String b, Integer c) {
        spawarkaCTR.setName(a);
        spawarkaCTR.setModel(b);
        spawarkaCTR.setKod(c);
    }

    @When("add it to arraylist")
    public void addToArrayList() {
        spawarkiService.addSpawarka(spawarkaCTR);
    }

    @When("set new name $name for spawarka with code $code")
    public void setNewNameForSpawarka(String name, Integer code) {
        spawarkiService.renameSpawarka(code, name);
    }

    @Then("adding should return $name for Spawarka object with code $code")
    public void shouldAdd(String name, int sprawarkaCode) {
        SpawarkaCTR spawarkaCTR = spawarkiService.getByID(sprawarkaCode);
        assertEquals(name, spawarkaCTR.getName());
    }

    @Then("check if data has been updated for spawarka with code $code and new name $name")
    public void checkIfUpdated(Integer code, String name) {
        SpawarkaCTR spawarkaCTR = spawarkiService.getByID(code);
        assertEquals(name, spawarkaCTR.getName());
    }
}
