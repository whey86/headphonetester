package com.erikle2.headphonetester.model.entities;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class EarPhonesTest {

    private String headphones;

    private String device;
    private User user;
    private List<TestPart> test = new ArrayList<TestPart>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The headphones
     */
    public String getHeadphones() {
        return headphones;
    }

    /**
     *
     * @param headphones
     * The headphones
     */
    public void setHeadphones(String headphones) {
        this.headphones = headphones;
    }

    /**
     *
     * @return
     * The device
     */
    public String getDevice() {
        return device;
    }

    /**
     *
     * @param device
     * The device
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The test
     */
    public List<TestPart> getTest() {
        return test;
    }

    /**
     *
     * @param test
     * The test
     */
    public void setTest(List<TestPart> test) {
        this.test = test;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


