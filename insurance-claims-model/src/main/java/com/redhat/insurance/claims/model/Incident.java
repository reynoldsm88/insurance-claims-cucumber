package com.redhat.insurance.claims.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Incident implements Serializable {

    public enum Type {
        COLLISION, THEFT, ROBBERY, ENVIRONMENTAL
    }

    private static final long serialVersionUID = 8558085574111931158L;

    private long id;
    private long subscriberId;
    private String description;
    private Type type;
    private LocalDate dateOccurred;

    public Incident() {
        super();
    }

    public long getId() {
        return this.id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId( long subscriberId ) {
        this.subscriberId = subscriberId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType( Type type ) {
        this.type = type;
    }

    public LocalDate getDateOccurred() {
        return dateOccurred;
    }

    public void setDateOccurred( LocalDate dateOccurred ) {
        this.dateOccurred = dateOccurred;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( dateOccurred == null ) ? 0 : dateOccurred.hashCode() );
        result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
        result = prime * result + (int) ( subscriberId ^ ( subscriberId >>> 32 ) );
        result = prime * result + (int) ( id ^ ( id >>> 32 ) );
        result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
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
        Incident other = (Incident) obj;
        if ( dateOccurred == null ) {
            if ( other.dateOccurred != null )
                return false;
        }
        else if ( !dateOccurred.equals( other.dateOccurred ) )
            return false;
        if ( description == null ) {
            if ( other.description != null )
                return false;
        }
        else if ( !description.equals( other.description ) )
            return false;
        if ( subscriberId != other.subscriberId )
            return false;
        if ( type != other.type )
            return false;
        return true;
    }

}