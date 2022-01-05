<p style="margin: 5px; font-weight: bold; font-size: x-large;">Search by player names and match dates</p>
<form class="mb-3" action="chess" method="post">
    <div class="from-group">
        <label class="form-label">Player 1</label>
        <input name="player1Name" type="text" class="form-control">
    </div>

    <div class="from-group">
        <label class="form-label">Player 2</label>
        <input name="player2Name" type="text" class="form-control">
    </div>
</br>
<p>Please select match winner:</p>
<div>
    <input type="radio" name="winner" value="1" id = "player1">
    <label for="player1">Player 1</label>
    </br>
    <input type="radio" name="winner" value="2" id = "player2">
    <label for="player2">Player 2</label>
    </br>
    <input type="radio" name="winner" value="3" id = "draw">
    <label for="draw">Draw</label>
</div>
</br>
    <div class="from-group">
        <label class="form-label">Date</label>
        <input name="date" type="date" class="form-control">
    </div>
    <div class="from-group">
        <label class="form-label">Time</label>
        <input name="time" type="time" class="form-control">
    </div>
    </br>
    <button type="submit" style="margin-top: 10px;  margin-bottom: 10px;" class="btn btn-primary">Search</button>
    </br>
</form>
</br>