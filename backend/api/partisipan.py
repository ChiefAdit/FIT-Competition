import falcon

from query.partisipan import insert_partisipan

class PartisipanResource:
    def on_post(self, req, resp):
        id_tournamen = req.media['id_tournamen'],
        id_user = req.media['id_user'],
        nama = req.media['name']
        alamat = req.media['alamat']
        no_telp = req.media['no_telp']
        gambar = req.media['gambar']
        
        if insert_partisipan(id_tournamen, id_user,nama, alamat, no_telp, gambar):
            resp.media = {
                'status': falcon.HTTP_200,
                'message': 'Partisipan berhasil ditambahkan'
            }
        else:
            resp.media = {
                'status': falcon.HTTP_500,
                'message': 'Partisipan gagal ditambahkan'
            }