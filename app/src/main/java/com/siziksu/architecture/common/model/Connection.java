package com.siziksu.architecture.common.model;

/**
 * The type Connection.
 */
public class Connection {

    private boolean connected;
    private String name;

    /**
     * Instantiates a new Connection.
     *
     * @param connected true or false
     * @param name      the name
     */
    public Connection(boolean connected, String name) {
        this.connected = connected;
        this.name = name;
    }

    /**
     * Is connected.
     *
     * @return true or false
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Sets connected.
     *
     * @param connected true or false
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}

