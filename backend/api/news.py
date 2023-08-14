import falcon, jwt, json
from datetime import datetime, timedelta, timezone
#import query order

from query.news import query_add_news,query_delete_news,query_update_news, query_get_all_news

class AddNews:
    def on_post(self, req, resp):
        judul = req.media.get('judul')
        isi = req.media.get('isi')
        gambar = req.media.get('gambar')
        kategori = req.media.get('kategori') 
        tanggal = req.media.get('tanggal')
        
        news = query_add_news(judul, gambar, isi ,kategori,tanggal  )
        if news is True:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'News berhasil ditambahkan'}
        else:
            resp.status = falcon.HTTP_401
            resp.media = {'message': 'News gagal ditambahkan'}

class DeleteNews:
    def on_post(self,req,resp):
        id = req.media.get('id')
        if not id:
            resp.status = falcon.HTTP_BAD_REQUEST
            return
        news = query_delete_news(id)
        if news is True:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'News berhasil dihapus'}
        else:
            resp.status = falcon.HTTP_401
            resp.media = {'message': 'News gagal dihapus'}

class UpdateNews:
    def on_post(self,req,resp):
        id = req.media.get('id')
        title = req.media.get('judul')
        content = req.media.get('isi')
        gambar = req.media.get('gambar')
        
        if not id or not title or not content:
            resp.status = falcon.HTTP_BAD_REQUEST
            return
        news = query_update_news(id, title, content)
        if news is True:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'News berhasil diupdate'}
        else:
            resp.status = falcon.HTTP_401
            resp.media = {'message': 'News gagal diupdate'}

class GetNews:
    def on_get(self, req, resp):
        news = query_get_all_news()
        if news is not None:
            resp.status = falcon.HTTP_200
            resp.body = json.dumps({'message': 'News berhasil ditampilkan', 'data': news})
        else:
            resp.status = falcon.HTTP_401
            resp.body = json.dumps({'message': 'News gagal ditampilkan'})