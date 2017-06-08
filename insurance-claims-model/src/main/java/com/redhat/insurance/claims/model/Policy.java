package com.redhat.insurance.claims.model;

import java.io.Serializable;
import java.util.Set;

public class Policy implements Serializable {

    private static final long serialVersionUID = 9189019082755625837L;

    private long id;
    private String name;
    private long subscriberId;
    private Set<Coverage> coverages;

    public Policy() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId( long subscriberId ) {
        this.subscriberId = subscriberId;
    }

    public Set<Coverage> getCoverages() {
        return coverages;
    }

    public void setCoverages( Set<Coverage> coverages ) {
        this.coverages = coverages;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( coverages == null ) ? 0 : coverages.hashCode() );
        result = prime * result + (int) ( id ^ ( id >>> 32 ) );
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        result = prime * result + (int) ( subscriberId ^ ( subscriberId >>> 32 ) );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Policy other = (Policy) obj;
        if ( coverages == null ) {
            if ( other.coverages != null )
                return false;
        }
        else if ( !coverages.equals( other.coverages ) )
            return false;
        if ( id != other.id )
            return false;
        if ( name == null ) {
            if ( other.name != null )
                return false;
        }
        else if ( !name.equals( other.name ) )
            return false;
        if ( subscriberId != other.subscriberId )
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Policy [id=" + id + ", name=" + name + ", subscriberId=" + subscriberId + ", coverages=" + coverages + "]";
    }

}