import falcon, jwt
from datetime import datetime, timedelta, timezone


from query.tournamen import query_add_tournamen, query_add_partisipan, query_get_tournamen_by_filter, query_get_all_tournamen

class AddTournamen:
    def on_post(self, req, resp):
        name= req.media.get('name')
        description = req.media.get('desciption')
        fee = req.media.get('fee')
        lokasi = req.media.get('lokasi')
        jenis = req.media.get('jenis')
        gambar = req.media.get('gambar')
        partisipan = req.media.get('partisipan')
        anggota_team = req.media.get('anggota_team')
        whatsapp = req.media.get('whatsapp')
        linkgroup = req.media.get('linkgroup')
        tanggal = req.media.get('tanggal')
        bracket = req.media.get('bracket')
        
        
        tournamen = query_add_tournamen(name, description, fee, lokasi, jenis, gambar,  partisipan, anggota_team, whatsapp, linkgroup, tanggal, bracket)
        if tournamen is True:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'pembuatan turnamen berhasil'}

class Daftarturnamen :
    def on_post (self, req, resp):
        id_turnamen = req.media.get('id_turnamen')
        id_user = req.media.get('id_user')
        name_team = req.media.get('name_team')
        anggota_teams = req.media.get('anggota_teams')
        if not id_turnamen or not id_user or not name_team or not anggota_teams:
            resp.status = falcon.HTTP_BAD_REQUEST
            return
        partisipan = query_add_partisipan(id_turnamen, id_user, name_team, anggota_teams)
        if partisipan is True:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'pembuatan partisipan berhasil'}

class FilterTournamen:
    def on_post(self, req, resp):
        name = req.media.get('name')
        jenis = req.media.get('jenis')
        lokasi = req.media.get('lokasi')
        
        tournamen = query_get_tournamen_by_filter(name, jenis, lokasi)
        if tournamen is not None:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'get turnamen berhasil', 'tournamen': tournamen}
        else:
            resp.status = falcon.HTTP_401
            resp.media = {'message': 'get turnamen gagal'}
            
class GetAllTournamen:
    def on_get(self, req, resp):
        tournamen = query_get_all_tournamen()
        if tournamen is not None:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'get all turnamen berhasil', 'tournamen': tournamen}
        else:
            resp.status = falcon.HTTP_401
            resp.media = {'message': 'get all turnamen gagal'}