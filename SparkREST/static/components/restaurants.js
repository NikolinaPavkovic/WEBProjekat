Vue.component("restaurants", {
	data: function() {
		return {
			restaurants: null
		}
	},
	template: `
	<div>
		<h3>Restorani:</h3>
		<table class="styled-table">
			<thead>
				<tr bgcolor="lightgrey">
					<th>Logo</th>
					<th>Naziv</th>
					<th>Tip</th>
					<th>Status</th>
					<th>Lokacija</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="r in restaurants">
					<td></td>
					<td>{{r.name}}</td>
					<td>{{r.type}}</td>
					<td>{{r.status}}</td>
					<td>{{r.location}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	`,
	mounted() {
		axios
			.get('rest/restaurants/')
			.then(response => (this.restaurants = response.data))
	}
});