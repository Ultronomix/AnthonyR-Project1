--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

-- Started on 2022-09-10 15:50:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE postgres;
--
-- TOC entry 3354 (class 1262 OID 13754)
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 3354
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 25615)
-- Name: app_users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.app_users (
    user_id uuid DEFAULT gen_random_uuid() NOT NULL,
    given_name character varying NOT NULL,
    surname character varying NOT NULL,
    email character varying(255) NOT NULL,
    username character varying(25) NOT NULL,
    password character varying NOT NULL,
    role_id uuid NOT NULL,
    is_active boolean NOT NULL
);


ALTER TABLE public.app_users OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 25627)
-- Name: ers_reimbursements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ers_reimbursements (
    reimb_id uuid DEFAULT gen_random_uuid() NOT NULL,
    amount numeric(6,2) NOT NULL,
    submitted timestamp without time zone NOT NULL,
    resolved timestamp without time zone,
    description character varying(250) NOT NULL,
    receipt bytea NOT NULL,
    payment_id uuid NOT NULL,
    author_id uuid NOT NULL,
    resolver_id uuid NOT NULL,
    status_id uuid NOT NULL,
    type_id uuid NOT NULL
);


ALTER TABLE public.ers_reimbursements OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 25635)
-- Name: reimbursement_statuses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reimbursement_statuses (
    status_id uuid DEFAULT gen_random_uuid() NOT NULL,
    status character varying NOT NULL
);


ALTER TABLE public.reimbursement_statuses OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 25645)
-- Name: reimbursement_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reimbursement_types (
    type_id uuid DEFAULT gen_random_uuid() NOT NULL,
    type character varying NOT NULL
);


ALTER TABLE public.reimbursement_types OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 25607)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    role_id uuid DEFAULT gen_random_uuid() NOT NULL,
    role character varying NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 3190 (class 2606 OID 25624)
-- Name: app_users app_users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_users
    ADD CONSTRAINT app_users_email_key UNIQUE (email);


--
-- TOC entry 3192 (class 2606 OID 25622)
-- Name: app_users app_users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_users
    ADD CONSTRAINT app_users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3194 (class 2606 OID 25626)
-- Name: app_users app_users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_users
    ADD CONSTRAINT app_users_username_key UNIQUE (username);


--
-- TOC entry 3196 (class 2606 OID 25634)
-- Name: ers_reimbursements ers_reimbursements_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ers_reimbursements
    ADD CONSTRAINT ers_reimbursements_pkey PRIMARY KEY (reimb_id);


--
-- TOC entry 3198 (class 2606 OID 25644)
-- Name: reimbursement_statuses reimbursement_statuses_Status_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reimbursement_statuses
    ADD CONSTRAINT "reimbursement_statuses_Status_key" UNIQUE (status);


--
-- TOC entry 3200 (class 2606 OID 25642)
-- Name: reimbursement_statuses reimbursement_statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reimbursement_statuses
    ADD CONSTRAINT reimbursement_statuses_pkey PRIMARY KEY (status_id);


--
-- TOC entry 3202 (class 2606 OID 25652)
-- Name: reimbursement_types reimbursement_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reimbursement_types
    ADD CONSTRAINT reimbursement_types_pkey PRIMARY KEY (type_id);


--
-- TOC entry 3204 (class 2606 OID 25654)
-- Name: reimbursement_types reimbursement_types_type_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reimbursement_types
    ADD CONSTRAINT reimbursement_types_type_key UNIQUE (type);


--
-- TOC entry 3186 (class 2606 OID 25676)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (role_id);


--
-- TOC entry 3188 (class 2606 OID 25614)
-- Name: user_roles user_roles_role_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_key UNIQUE (role);


--
-- TOC entry 3205 (class 2606 OID 25677)
-- Name: app_users app_users_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.app_users
    ADD CONSTRAINT app_users_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.user_roles(role_id);


--
-- TOC entry 3206 (class 2606 OID 25655)
-- Name: ers_reimbursements ers_reimbursements_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ers_reimbursements
    ADD CONSTRAINT ers_reimbursements_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.app_users(user_id);


--
-- TOC entry 3207 (class 2606 OID 25660)
-- Name: ers_reimbursements ers_reimbursements_resolver_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ers_reimbursements
    ADD CONSTRAINT ers_reimbursements_resolver_id_fkey FOREIGN KEY (resolver_id) REFERENCES public.app_users(user_id);


--
-- TOC entry 3208 (class 2606 OID 25665)
-- Name: ers_reimbursements ers_reimbursements_status_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ers_reimbursements
    ADD CONSTRAINT ers_reimbursements_status_id_fkey FOREIGN KEY (status_id) REFERENCES public.reimbursement_statuses(status_id);


--
-- TOC entry 3209 (class 2606 OID 25670)
-- Name: ers_reimbursements ers_reimbursements_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ers_reimbursements
    ADD CONSTRAINT ers_reimbursements_type_id_fkey FOREIGN KEY (type_id) REFERENCES public.reimbursement_types(type_id);


-- Completed on 2022-09-10 15:50:11

--
-- PostgreSQL database dump complete
--

