import connectdb
import bcrypt

def hash_password(password):
    salt = bcrypt.gensalt()
    hashed_password = bcrypt.hashpw(password.encode('utf-8'), salt)
    return hashed_password.decode('utf-8')

def check_password(password, hashed_password):
    return bcrypt.checkpw(password.encode('utf-8'), hashed_password.encode('utf-8'))

def query_login_admin(email, password):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT  id, password FROM adit_fit_users WHERE email = %s AND role = 'admin'", (email,))
        admin = cur.fetchone()
        cur.close()
        if admin is not None and check_password(password, admin[1]):
            return admin
        else:
            return None
    else:
        return None

def query_login_or_register_customer(id):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT id FROM adit_fit_users WHERE id = %s AND role = 'customer'", (id,))
        customer = cur.fetchone()
        cur.close()
        if customer:
            return customer
        else:
            # Register the user
            cur = conn.cursor()
            cur.execute("INSERT INTO adit_fit_users (id, role) VALUES (%s, 'customer')", (id,))
            conn.commit()
            cur.close()
            # Fetch the newly registered customer's data
            cur = conn.cursor()
            cur.execute("SELECT  id  FROM adit_fit_users WHERE id = %s AND role = 'customer'", (id,))
            customer = cur.fetchone()
            cur.close()
            return customer
    else:
        print("Connection Failed")
        return None

def query_register_admin( email, password, name, phone,gambar ):
    conn=connectdb.test_connection()
    if conn is not None:
        hashed_password = hash_password(password)
        cur = conn.cursor()
        cur.execute("INSERT INTO adit_fit_users (id, password, name, phone, gambar, role ) VALUES (%s, %s, %s, %s, %s, 'admin')", (email, hashed_password, name,  phone,gambar))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False

def query_get_all_users():
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT id, email, name FROM adit_fit_users WHERE role = 'customer'")
        rows = cur.fetchall()
        cur.close()
        return rows
    else:
        return None
#fungsi untuk mengambil data user berdasarkan id dan role customer
def query_get_user_by_id(id):
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT id, email, password, name, role FROM adit_fit_users WHERE id = %s AND role = 'customer'", (id,))
        user = cur.fetchone()
        cur.close()
        return user
    else:
        return None
#fungsi untuk mengambil data user berdasarkan email dan role admin
def query_get_admin_by_id(id):
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT id, email, password, name, role FROM adit_fit_users WHERE id = %s AND role = 'admin'", (id,))
        user = cur.fetchone()
        cur.close()
        return user
    else:
        return None

def query_update_user_by_email(email, password, name):
    conn=connectdb.test_connection()
    if conn is not None:
        hashed_password = hash_password(password)
        cur = conn.cursor()
        cur.execute("UPDATE adit_fit_users SET email = %s, password = %s, name = %s WHERE email = %s AND role = 'customer'", (email, hashed_password, name, email))
        conn.commit()
        cur.close()
        return True
    else:
        return False

def query_delete_user_by_email(id):
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("DELETE FROM adit_fit_users WHERE id = %s AND role = 'customer'", (id,))
        conn.commit()
        cur.close()
        return True
    else:
        return False

#hitung jumlah data Customer
def query_count_users():
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT COUNT(*) FROM adit_fit_users WHERE role = 'customer'")
        count = cur.fetchone()
        cur.close()
        return count[0]
    else:
        return None
#hitung jumlah data Admin
def query_count_admin():
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT COUNT(*) FROM adit_fit_users WHERE role = 'admin'")
        count = cur.fetchone()
        cur.close()
        return count[0]
    else:
        return None

#hitung semua data user 
def query_count_all_users():
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT COUNT(*) FROM adit_fit_users")
        count = cur.fetchone()
        cur.close()
        return count[0]
    else:
        return None