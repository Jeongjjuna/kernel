package yjh.board.member.infrastructure.memory;

import org.springframework.stereotype.Repository;
import yjh.board.db.DataRepository;
import yjh.board.member.infrastructure.MemberEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements DataRepository<MemberEntity, Long> {

    private static long index = 0;

    private final List<MemberEntity> dataList = new ArrayList<>();

    private final Comparator<MemberEntity> sort = (o1, o2) -> Long.compare(o1.getId(), o2.getId());

    @Override
    public MemberEntity save(MemberEntity entity) {
        Objects.requireNonNull(entity, "[ERROR] entity require non-null");

        synchronized (this) {
            var prevData = dataList.stream()
                    .filter(it -> it.getId().equals(entity.getId()))
                    .findFirst();

            if (prevData.isPresent()) {
                dataList.remove(prevData.get());
                dataList.add(entity);
            } else {
                index++;
                entity.setId(index);
                dataList.add(entity);
            }
        }

        return entity;
    }

    @Override
    public Optional<MemberEntity> findById(Long id) {
        return dataList.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<MemberEntity> findAll() {
        return dataList
                .stream()
                .sorted(sort)
                .toList();
    }

    @Override
    public void delete(Long id) {
        synchronized (this) {
            var deleteEntity = dataList.stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();

            deleteEntity.ifPresent(dataList::remove);
        }
    }
}
