<template>
  <div ref="mapContainer" class="map-container" />
  <div class="form">
    <Form
      @submit="setCircleRadius"
    />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import maplibregl from 'maplibre-gl';
import Form from './Form.vue';
import 'maplibre-gl/dist/maplibre-gl.css';

const mapContainer = ref(null);
const map = ref(null);
const marker = ref(null);
const circle = ref(null);
const center = [2.3522, 48.8566]; // Coordonnées de Paris, par exemple

onMounted(() => {
  map.value = new maplibregl.Map({
    container: mapContainer.value,
    style: 'https://api.maptiler.com/maps/streets/style.json?key=get_your_own_OpIi9ZULNHzrESv6T2vL', // Utilisez votre propre style si nécessaire
    center: center,
    zoom: 13
  });

  map.value.on('click', (e) => {

    placeMarker(e);
    setCircleRadius(0.01);

    

    // Ajoutez ici la logique pour créer ou ajuster le cercle
  });

  // Ajoutez la logique pour déplacer le marqueur et créer un cercle
});

const setCircleRadius = (radius) => {
  if (marker.value) {
    const center = marker.value.getLngLat();

    // Supprime le cercle existant s'il existe
    if (map.value.getLayer('circle-layer')) {
      map.value.removeLayer('circle-layer');
      map.value.removeSource('circle-source');
    }

    // Création d'un nouveau cercle
    const circleFeature = createGeoJSONCircle(center, radius);
    map.value.addSource('circle-source', {
      'type': 'geojson',
      'data': circleFeature
    });

    map.value.addLayer({
      'id': 'circle-layer',
      'type': 'fill',
      'source': 'circle-source',
      'layout': {},
      'paint': {
        'fill-color': '#007cbf',
        'fill-opacity': 0.5
      }
    });
  }
};

// Fonction pour créer un cercle GeoJSON
function createGeoJSONCircle(center, radiusInKm) {
  const points = 64;
  const coords = {
    latitude: center.lat,
    longitude: center.lng
  };

  const km = radiusInKm;

  const ret = [];
  const distanceX = km / (111.320 * Math.cos((coords.latitude * Math.PI) / 180));
  const distanceY = km / 110.574;

  let theta, x, y;
  for (let i = 0; i < points; i += 1) {
    theta = (i / points) * (2 * Math.PI);
    x = distanceX * Math.cos(theta);
    y = distanceY * Math.sin(theta);

    ret.push([coords.longitude + x, coords.latitude + y]);
  }
  ret.push(ret[0]);

  return {
    type: 'Feature',
    geometry: {
      type: 'Polygon',
      coordinates: [ret]
    }
  };
}

const placeMarker =(e)=>{
  if (marker.value) {
    marker.value.remove(); // Supprime le marqueur précédent
  }
  marker.value = new maplibregl.Marker()
    .setLngLat(e.lngLat)
    .addTo(map.value);
}

const removeMarker = () => {
  if (marker.value) {
    marker.value.remove();
    marker.value = null;
  }
};

const updateCircleRadius = (newRadius) => {
  if (marker.value && map.value.getLayer('circle-layer')) {
    // Supprimer l'ancien cercle
    map.value.removeLayer('circle-layer');
    map.value.removeSource('circle-source');

    // Créer un nouveau cercle avec le nouveau rayon
    const center = marker.value.getLngLat();
    const newCircleFeature = createGeoJSONCircle(center, newRadius);

    map.value.addSource('circle-source', {
      'type': 'geojson',
      'data': newCircleFeature
    });

    map.value.addLayer({
      'id': 'circle-layer',
      'type': 'fill',
      'source': 'circle-source',
      'layout': {},
      'paint': {
        'fill-color': '#007cbf',
        'fill-opacity': 0.5
      }
    });
  }
};
</script>

<style>
.map-container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  z-index: 2;
}
.form {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 100;
}
</style>
