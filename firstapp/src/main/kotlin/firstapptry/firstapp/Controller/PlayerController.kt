package firstapptry.firstapp.Controller

import firstapptry.firstapp.Entity.Player
import firstapptry.firstapp.Service.PlayerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("players")
@RestController
class PlayerController(val service: PlayerService) {

    @GetMapping
    fun getAllPlayers() = service.getAll()

    @GetMapping("/{id}")
    fun getPlayer(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePlayer(@RequestBody player: Player): Player = service.create(player)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePlayer(@PathVariable id: Long) = service.remove(id)

    @PutMapping("/{id}")
    fun updatePlayer(
        @PathVariable id: Long, @RequestBody player: Player
    ) = service.update(id, player)
}