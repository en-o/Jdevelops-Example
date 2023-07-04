-- spring-security-oauth2-authorization-server
-- org\springframework\security\oauth2\server\authorization\oauth2-authorization-consent-schema.sql
-- org\springframework\security\oauth2\server\authorization\oauth2-authorization-schema.sql
-- org\springframework\security\oauth2\server\authorization\client\oauth2-registered-client-schema.sql

CREATE TABLE `oauth2_authorization_consent` (
                                                `registered_client_id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                                                `principal_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
                                                `authorities` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
                                                PRIMARY KEY (`registered_client_id`,`principal_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


/*
IMPORTANT:
    If using PostgreSQL, update ALL columns defined with 'blob' to 'text',
    as PostgreSQL does not support the 'blob' data type.
*/
CREATE TABLE `oauth2_authorization` (
                                        `id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                                        `registered_client_id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                                        `principal_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
                                        `authorization_grant_type` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                                        `authorized_scopes` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                        `attributes` blob,
                                        `state` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                        `authorization_code_value` blob,
                                        `authorization_code_issued_at` timestamp NULL DEFAULT NULL,
                                        `authorization_code_expires_at` timestamp NULL DEFAULT NULL,
                                        `authorization_code_metadata` blob,
                                        `access_token_value` blob,
                                        `access_token_issued_at` timestamp NULL DEFAULT NULL,
                                        `access_token_expires_at` timestamp NULL DEFAULT NULL,
                                        `access_token_metadata` blob,
                                        `access_token_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                        `access_token_scopes` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                        `oidc_id_token_value` blob,
                                        `oidc_id_token_issued_at` timestamp NULL DEFAULT NULL,
                                        `oidc_id_token_expires_at` timestamp NULL DEFAULT NULL,
                                        `oidc_id_token_metadata` blob,
                                        `refresh_token_value` blob,
                                        `refresh_token_issued_at` timestamp NULL DEFAULT NULL,
                                        `refresh_token_expires_at` timestamp NULL DEFAULT NULL,
                                        `refresh_token_metadata` blob,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `oauth2_registered_client` (
                                            `id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                                            `client_id` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
                                            `client_id_issued_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                            `client_secret` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                            `client_secret_expires_at` timestamp NULL DEFAULT NULL,
                                            `client_name` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
                                            `client_authentication_methods` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
                                            `authorization_grant_types` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
                                            `redirect_uris` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                            `scopes` varchar(1000) COLLATE utf8mb4_general_ci NOT NULL,
                                            `client_settings` varchar(2000) COLLATE utf8mb4_general_ci NOT NULL,
                                            `token_settings` varchar(2000) COLLATE utf8mb4_general_ci NOT NULL,
                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
