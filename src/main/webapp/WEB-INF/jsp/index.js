function PolygonCreator(map) {
    //create pen to draw on map
    this.map = map;
    this.pen = new Pen(this.map);
    this.latLng = '';
    var thisOjb = this;
    this.event = google.maps.event.addListener(thisOjb.map, 'click', function (event) {
        thisOjb.latLng = event.latLng;
        thisOjb.pen.draw(thisOjb.latLng);
    });

    this.getDot = function () {
        return (this.latLng+"").replace(/[()]/g,"");
    }

    this.getData = function () {
        return this.pen.getData() != null ? (this.pen.getData()+"").replace(/\)\(/g,";").replace(/[()]/g,"") : '';
    }

    this.showData = function () {
        return this.pen.getData();
    }

    this.showColor = function () {
        return this.pen.getColor();
    }

    //destroy the pen
    this.destroy = function () {
        this.pen.deleteMis();
        if (null != this.pen.polygon) {
            this.pen.polygon.remove();
        }
        google.maps.event.removeListener(this.event);
    }

    this.drawPolygon = function (latLngList) {
        $.each(latLngList, function (index, value) {
            thisOjb.pen.draw(value);
        });
        this.pen.drawPloygon(this.pen.listOfDots);
    }

    this.getPolygon = function () {
        return this.pen.polygon.getPolygonObj();
    }

}