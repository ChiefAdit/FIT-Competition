import connectdb 

      

def query_make_order (user_id, tournamen_id):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("INSERT INTO adit_fit_order(user_id, tournament_id, status) VALUES (%s, %s, 'pending')", (user_id, tournamen_id))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
    
def query_get_all_order():
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT id, user_id, tournament_id, status FROM adit_fit_order")
        order = cur.fetchall()
        cur.close()
        return order
    else:
        print("Connection Failed")
        return None
    
def query_get_order_by_id(user_id):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT o.id, o.user_id, o.tournament_id, o.status, t.gambar, t.id_user, t.name, t.description, t.fee, t.lokasi, t.jenis, t.partisipan, t.anggota_team, t.whatsapp, t.linkgroup, t.tanggal  FROM adit_fit_order o JOIN adit_fit_tournamen t ON o.tournament_id = t.id  WHERE o.user_id = %s", (user_id,))
        orders = cur.fetchall()
        cur.close()
        return orders
    else:
        print("Connection Failed")
        return None
    
#ubah status order menjadi 'ongoing' by turnamen id
def query_update_order_status_by_tournamen_id(tournamen_id):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()

        # Dapatkan semua order yang terkait dengan turnamen tertentu
        cur.execute("SELECT id, user_id, tournament_id, status FROM adit_fit_order WHERE tournament_id = %s", (tournamen_id,))
        orders = cur.fetchall()

        # Periksa setiap order
        for order in orders:
            order_id, user_id, tournamen_id, status = order

            # Periksa apakah user memiliki status 'admin'
            cur.execute("SELECT ROLE FROM adit_fit_users WHERE id = %s", (user_id,))
            user_role = cur.fetchone()[0]

            if user_role == 'admin':
                # Ubah status order menjadi 'ongoing'
                cur.execute("UPDATE adit_fit_order SET status = 'ongoing' WHERE id = %s", (order_id,))

        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False

#read order by user id
def query_read_by_user_id(user_id):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT id, user_id, tournament_id, status FROM adit_fit_order WHERE user_id = %s", (user_id,))
        order = cur.fetchall()
        cur.close()
        return order
    else:
        print("Connection Failed")
        return None
    

