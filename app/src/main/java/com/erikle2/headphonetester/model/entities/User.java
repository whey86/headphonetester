package com.erikle2.headphonetester.model.entities;

/**
 * Created by Erik on 19/04/2016.
 */

import java.util.HashMap;
import java.util.Map;


public class User {

    private String id;
    private String usename;
    private String email;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    /**
     *
     * @return
     * The id
     */

    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The usename
     */
    public String getUsename() {
        return usename;
    }

    /**
     *
     * @param usename
     * The usename
     */
    public void setUsename(String usename) {
        this.usename = usename;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
