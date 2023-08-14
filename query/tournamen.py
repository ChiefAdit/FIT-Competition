import connectdb


def query_add_tournamen(name, description, fee, lokasi, jenis, gambar,  partisipan, anggota_team, whatsapp, linkgroup, tanggal, bracket):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("INSERT INTO adit_fit_tournamen (name, description, fee, lokasi, jenis, gambar, status, partisipan, anggota_team, whatsapp, linkgroup, tanggal, bracket) VALUES (%s, %s, %s, %s, %s, %s, 'on', %s, %s, %s, %s, %s, %s)", (name, description, fee, lokasi, jenis, gambar,  partisipan, anggota_team, whatsapp, linkgroup, tanggal, bracket))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False


#fungsi untuk memasukkan data partisipan tim ke dalam tabel adit_fit_partisipan saat mendaftar
def query_add_partisipan(id_turnamen, id_user, name_team, gambar, whatsapp):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()

        # Periksa jumlah partisipan yang sudah terdaftar untuk turnamen ini
        cur.execute("SELECT COUNT(*) FROM adit_fit_partisipan WHERE id_turnamen = %s", (id_turnamen,))
        count_partisipan = cur.fetchone()[0]

        # Ambil batas maksimal partisipan dari tabel adit_fit_tournamen berdasarkan id_turnamen
        cur.execute("SELECT partisipan FROM adit_fit_tournamen WHERE id = %s", (id_turnamen,))
        max_partisipan = cur.fetchone()[0]

        if count_partisipan >= max_partisipan:
            print("Jumlah partisipan telah mencapai batas maksimum.")
            return False

        # Memasukkan data partisipan tim (nama tim) ke dalam tabel adit_fit_partisipan
        cur.execute("INSERT INTO adit_fit_partisipan (id_turnamen, id_user, name_team, gambar, whatsapp) VALUES (%s, %s, %s, %s, %s) RETURNING id", (id_turnamen, id_user, name_team, gambar, whatsapp))
        partisipan_id = cur.fetchone()[0]

        # Jika jumlah partisipan setelah penambahan mencapai maksimum, ubah status turnamen menjadi 'full'
        if count_partisipan + 1 >= max_partisipan:
            cur.execute("UPDATE adit_fit_tournamen SET status = 'full' WHERE id = %s", (id_turnamen,))

        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False


def query_get_all_tournamen():
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT id, name, description, fee, lokasi, jenis, gambar, status, partisipan, anggota_team, whatsapp, linkgroup, tanggal, bracket FROM adit_fit_tournamen WHERE status = 'on'")
        columns = [desc[0] for desc in cur.description]
        tournamen = [dict(zip(columns, row)) for row in cur.fetchall()]
        cur.close()
        return tournamen
    else:
        print("Connection Failed")
        return None

def query_get_tournamen_by_id(id):
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT id, name, description, fee, lokasi, jenis, gambar, status, partisipan, anggota_team, whatsapp, linkgroup,tanggal, bracket FROM adit_fit_tournamen WHERE id = %s", (id,))
        tournamen = cur.fetchone()
        cur.close()
        return tournamen
    else:
        print("Connection Failed")
        return None
    

#fungsi untuk mengambil data turnamen berdasarkan filter untuk user
def query_get_tournamen_by_filter(name=None, jenis=None, lokasi=None):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()

        # Modifikasi kondisi WHERE dengan ILIKE dan wildcard
        query = "SELECT id, name, description, fee, lokasi, jenis, gambar, status, partisipan, anggota_team, whatsapp, linkgroup, tanggal, bracket FROM adit_fit_tournamen"

        # Tambahkan kondisi untuk jenis dan lokasi jika nilainya tidak kosong
        params = []
        conditions = []

        if name is not None and name != "":
            conditions.append("name ILIKE %s")
            params.append("%" + name + "%")

        if jenis is not None and jenis != "":
            conditions.append("jenis = %s")
            params.append(jenis)

        if lokasi is not None and lokasi != "":
            conditions.append("lokasi = %s")
            params.append(lokasi)

        if conditions:
            query += " WHERE " + " AND ".join(conditions)

        cur.execute(query, tuple(params))
        columns = [desc[0] for desc in cur.description]
        tournamen = [dict(zip(columns, row)) for row in cur.fetchall()]
        cur.close()
        return tournamen
    else:
        print("Connection Failed")
        return None



#fungsi untuk read data turnamen dimana status user adalah admin
def query_get_all_tournamen_admin():
    conn=connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("SELECT t.id, t.name, t.description, t.fee, t.lokasi, t.jenis, t.gambar, t.status, t.partisipan, t.anggota_team, t.whatsapp, t.linkgroup, t.tanggal, t.bracket FROM adit_fit_tournamen t JOIN adit_fit_users u ON t.id = u.id WHERE u.role = 'admin'")
        tournamen = cur.fetchall()
        cur.close()
        return tournamen
    else:
        print("Connection Failed")
        return None
    
#fungsi untuk mengubah data turnamen
def query_edit_tournamen(id, name, desciption, fee, lokasi, jenis, partisipan, gambar, whatsapp, linkgroup, tanggal, bracket):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("UPDATE adit_fit_tournamen SET name = %s, description = %s, fee = %s, lokasi = %s, jenis = %s, partisipan = %s, gambar = %s, whatsapp = %s, linkgroup = %s, tanggal = %s, bracket = %s WHERE id = %s", (name, desciption, fee, lokasi, jenis, partisipan, gambar, whatsapp,linkgroup, tanggal, bracket, id))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
    
#fungsi untuk menghapus data turnamen
def query_delete_tournamen(id):
    conn = connectdb.test_connection()
    if conn is not None:
        cur = conn.cursor()
        cur.execute("DELETE FROM adit_fit_tournamen WHERE id = %s", (id,))
        conn.commit()
        cur.close()
        return True
    else:
        print("Connection Failed")
        return False
    
#insert data turnamen



