CREATE SCHEMA IF NOT EXISTS tickety;

CREATE TABLE events (
                        record_id BIGINT PRIMARY KEY,
                        organization_id BIGINT NOT NULL,
                        title VARCHAR(255) NOT NULL,
                        description TEXT NOT NULL,
                        date TIMESTAMP NOT NULL,
                        location JSONB NOT NULL,
                        ticket_types JSONB NOT NULL,
                        total_capacity INT NOT NULL,
                        booked_count INT DEFAULT 0,
                        is_active BOOLEAN DEFAULT TRUE,
                        created_at TIMESTAMP DEFAULT NOW(),
                        updated_at TIMESTAMP NULL,
                        audit_info JSONB NULL DEFAULT '{}'
);
