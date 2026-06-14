package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());
        Assertions.assertThat(findItem).isEqualTo(saveItem);

    }

    @Test
    void find() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        //when
        itemRepository.save(item1);
        itemRepository.save(item2);
        List<Item> findAllItem = itemRepository.findAll();
        //then
        Assertions.assertThat(findAllItem.size()).isSameAs(2);
        Assertions.assertThat(findAllItem).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        itemRepository.save(item1);
        //when
        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(item1.getId(),updateParam);
        //then
        Assertions.assertThat(itemRepository.findById(1L).getItemName()).isEqualTo("item2");
        Assertions.assertThat(itemRepository.findById(1L).getPrice()).isEqualTo(20000);
        Assertions.assertThat(itemRepository.findById(1L).getQuantity()).isEqualTo(20);
    }

}