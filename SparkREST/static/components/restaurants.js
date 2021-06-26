Vue.component("restaurants", {
	data: function() {
		return {
			restaurants: null
		}
	},
	template: `
	<div>
		<h3>Prikaz restorana</h3>
		<table border="1">
			<tr bgcolor="lightgrey">
				<th>Logo</th>
				<th>Naziv</th>
				<th>Tip</th>
				<th>Status</th>
				<th>Lokacija</th>
			</tr>
			<tr v-for="r in restaurants">
				<td></td>
				<td>{{r.name}}</td>
				<td>{{r.type}}</td>
				<td>{{r.status}}</td>
				<td>{{r.location}}</td>
			</tr>
		</table>
	</div>
	`,
	mounted() {
		axios
			.get('rest/restaurants/')
			.then(response => (this.restaurants = response.data))
	}
});