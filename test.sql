PGDMP     
    8    	            y            pruebas    13.2 (Debian 13.2-1.pgdg100+1)    13.2     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16384    pruebas    DATABASE     [   CREATE DATABASE pruebas WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';
    DROP DATABASE pruebas;
                postgres    false                        2615    16385    propio    SCHEMA        CREATE SCHEMA propio;
    DROP SCHEMA propio;
                postgres    false            ?            1259    24718    client_id_seq    SEQUENCE     v   CREATE SEQUENCE propio.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE propio.client_id_seq;
       propio          postgres    false    6            ?            1259    24720    clients    TABLE     [  CREATE TABLE propio.clients (
    id integer NOT NULL,
    active boolean DEFAULT true,
    address character varying(255),
    created_at timestamp without time zone DEFAULT now(),
    last_name character varying(255),
    name character varying(255),
    phone character varying(255),
    updated_at timestamp without time zone DEFAULT now()
);
    DROP TABLE propio.clients;
       propio         heap    postgres    false    6            ?            1259    24646    clients    TABLE     2  CREATE TABLE public.clients (
    id integer NOT NULL,
    active boolean,
    address character varying(255),
    created_at timestamp without time zone,
    last_name character varying(255),
    name character varying(255),
    phone character varying(255),
    updated_at timestamp without time zone
);
    DROP TABLE public.clients;
       public         heap    postgres    false            ?            1259    24629    propio_clients    TABLE     9  CREATE TABLE public.propio_clients (
    id integer NOT NULL,
    active boolean,
    address character varying(255),
    created_at timestamp without time zone,
    last_name character varying(255),
    name character varying(255),
    phone character varying(255),
    updated_at timestamp without time zone
);
 "   DROP TABLE public.propio_clients;
       public         heap    postgres    false            ?          0    24720    clients 
   TABLE DATA           f   COPY propio.clients (id, active, address, created_at, last_name, name, phone, updated_at) FROM stdin;
    propio          postgres    false    204          ?          0    24646    clients 
   TABLE DATA           f   COPY public.clients (id, active, address, created_at, last_name, name, phone, updated_at) FROM stdin;
    public          postgres    false    202   ?       ?          0    24629    propio_clients 
   TABLE DATA           m   COPY public.propio_clients (id, active, address, created_at, last_name, name, phone, updated_at) FROM stdin;
    public          postgres    false    201   ?       ?           0    0    client_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('propio.client_id_seq', 355, true);
          propio          postgres    false    203                       2606    24727    clients clients_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY propio.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY propio.clients DROP CONSTRAINT clients_pkey;
       propio            postgres    false    204                       2606    24653    clients clients_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.clients DROP CONSTRAINT clients_pkey;
       public            postgres    false    202                       2606    24636 "   propio_clients propio_clients_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.propio_clients
    ADD CONSTRAINT propio_clients_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.propio_clients DROP CONSTRAINT propio_clients_pkey;
       public            postgres    false    201            ?   ?   x?????0D??+??D?c??U?YB??D?C??)ST*???zw?C5?S??1k?~j2$??J???iߺyP"b?I?@??K???4?????1?&?/??1?C??!̠N?eWx/?????W??t????L???7??~S???)W) ?ڱ??C??)?er¢k??~lv?      ?      x?????? ? ?      ?      x?????? ? ?     