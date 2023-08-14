import connectdb

def insert_partisipan(id_tournamen, id_user,nama, alamat, no_telp, gambar):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("INSERT INTO adit_fit_partisipan (id_turnamen, id_user,name_team, alamat, whatsapp, gambar) VALUES (%s, %s, %s, %s, %s, %s)", (id_tournamen, id_user,nama, alamat, no_telp, gambar))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
