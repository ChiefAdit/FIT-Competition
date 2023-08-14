from query.afifa import query_note, query_getall
import falcon
class afifah ():
    def on_post(self, req, resp):
        name = req.media['name']
        status = req.media['status']
        if not name or not status:
            resp.status = falcon.HTTP_BAD_REQUEST
            return
        add_note = query_note (name, status)
        if add_note is True:
            resp.status = falcon.HTTP_200
            resp.media = {'message': 'Note berhasil ditambahkan'}

class read():
    def on_get(self, req, resp):
        rows = query_getall()
        if rows is not False:
            resp.status = falcon.HTTP_200
            resp.media = rows
        else:
            resp.status = falcon.HTTP_500
            resp.media = {'message': 'Internal Server Error'}