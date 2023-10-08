<!-- QR Code Scanner -->

<template>

<h1> QR Test </h1>


    <div class="qrcode-container">
      <qrcode-stream @decode="decode" :track="drawOutline"></qrcode-stream>
    </div>


</template>

<script>
import {QrcodeStream} from 'vue-qrcode-reader'


export default {
  name: 'QRCodeScanner',
  components: {
    QrcodeStream
  },
  methods: {
    decode (content) {
      console.log(content)
    },

    drawOutline(decodedContent, context) {
      var points = [];

      for (var k in decodedContent) {
        switch(k) {
          case "topLeftCorner":
            points[0] = decodedContent[k];
            break;

          case "topRightCorner":
            points[1] = decodedContent[k];
            break;

            case "bottomRightCorner":
              points[2] = decodedContent[k];
              break;

            case "bottomLeftCorner":
              points[3] = decodedContent[k];
              break;
        }

        context.strokeStyle = "green";

        context.beginPath();

        context.moveTo(points[0].x, points[0].y);

        for (const {x, y} of points) {
          context.lineTo(x, y);
        }

        context.lineTo(points[0].x, points[0].y);
        context.closePath();
        context.stroke();
      }
      console.log(points);
      console.log(context);
    }
  }
}

</script>

<style scoped>
.qrcode-container {
  width: 300px; /* Set your desired width */
  height: 300px; /* Set your desired height */
  margin: 0 auto; /* Center the component on the page */
}
</style>