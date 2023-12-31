import connectdb
import os
from werkzeug.utils import secure_filename



#buat query untuk menampilankan semua data produk
def query_get_all_products():
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT name, description, CAST(price AS float) AS price, stock, product_id FROM .\"_672020277_ecommerce_products\"")
        rows = cur.fetchall()
        cur.close()
        products = []
        for row in rows:
            product = {
                "name": row[0],
                "description": row[1],
                "price": row[2],
                "stock": row[3],
                "product_id": row[4],
                "image": None # or assign a default image URL here

            }
            products.append(product)
        return products
    else:
        return None



"""query tambah produk berdasarkan tabel ini 
-- public."_672020277_ecommerce_products" definition

-- Drop table

-- DROP TABLE public."_672020277_ecommerce_products";

CREATE TABLE public."_672020277_ecommerce_products" (
	"name" varchar NOT NULL,
	description text NOT NULL,
	price numeric NOT NULL,
	stock int4 NOT NULL,
	product_id serial4 NOT NULL,
	CONSTRAINT "_672020277_ecommerce_products_pk" PRIMARY KEY (product_id),
	CONSTRAINT "_672020277_ecommerce_products_un" UNIQUE (product_id)
);

-- Permissions

ALTER TABLE public."_672020277_ecommerce_products" OWNER TO postgres;
GRANT ALL ON TABLE public."_672020277_ecommerce_products" TO postgres;
"""
def query_add_product(name, description, price, stock, image):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        
        # Simpan gambar ke folder di backend
        file_path = "../static/images/" + secure_filename(image.filename)
        image.save(file_path)
        
        # Simpan informasi produk ke database
        cur.execute("INSERT INTO _672020277_ecommerce_products (name, description, price, stock, image) VALUES (%s, %s, %s, %s, %s)", (name, description, price, stock, file_path))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False


#delete produk
def query_delete_product(product_id):
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("DELETE FROM _672020277_ecommerce_products WHERE product_id = %s", (product_id,))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False

#update produk
def query_update_product(name, description, price, stock, image, product_id):
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("UPDATE _672020277_ecommerce_products SET name = %s, description = %s, price = %s, stock = %s, image = %s WHERE product_id = %s", (name, description, price, stock, image, product_id))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False

#ambil data produk berdasarkan id
def query_get_product_by_id(product_id):
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        
        cur.execute("SELECT name, description, CAST(price AS float) AS price, stock, product_id, image FROM public.\"_672020277_ecommerce_products\" WHERE product_id = %s", (product_id,))
        product = cur.fetchone()
        cur.close()
        return product
    else:
        return None